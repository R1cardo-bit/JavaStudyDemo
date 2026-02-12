package payroll;


import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class EmployeeController {
    private final EmployeeRepository repository;
    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    // --- 接口 1: 获取所有员工 ---
    @GetMapping("/employees")
    List<Employee> all() {
        return repository.findAll(Sort.by(Sort.Direction.ASC,"joiningData"));
    }

    // --- 接口 2: 保存新员工 ---
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // --- 接口 3: 查询单个员工 ---
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repository.findById(id)
             .orElseThrow(() -> new EmployeeNotFoundException(id)); // 使用我们刚才定义的异常类
    }

    // --- 接口 4: 修改员工 ---
    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    // --- 接口 5: 删除员工 ---
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
