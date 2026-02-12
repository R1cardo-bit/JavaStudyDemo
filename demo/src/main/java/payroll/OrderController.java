package payroll;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class OrderController {
    private final OrderRepository orderRepository;

    OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/orders")
    List<Order> all() {
        return orderRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    Order one(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("找不到订单 " + id));
    }

    @PostMapping("/orders")
    Order newOrder(@RequestBody Order order) {
        order.setStatus(Status.IN_PROGRESS);
        Order newOrder = orderRepository.save(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED).getBody();
    }

    @DeleteMapping("/orders/{id}/cancel")
    ResponseEntity<?> cancel(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("找不到订单 " + id));

        // 核心逻辑检查：只有在进行中的订单才能取消
        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.CANCELLED);
            return ResponseEntity.ok(orderRepository.save(order));
        }

        // 如果状态不对（如已经是已完成状态），返回 405 Method Not Allowed 或自定义错误
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("无法取消状态为 " + order.getStatus() + " 的订单");
    }

    /**
     * 完成订单的业务逻辑
     */
    @PutMapping("/orders/{id}/complete")
    ResponseEntity<?> complete(@PathVariable Long id) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("找不到订单 " + id));

        // 核心逻辑检查：只有在进行中的订单才能完成
        if (order.getStatus() == Status.IN_PROGRESS) {
            order.setStatus(Status.COMPLETED);
            return ResponseEntity.ok(orderRepository.save(order));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body("无法完成状态为 " + order.getStatus() + " 的订单");
    }
}
