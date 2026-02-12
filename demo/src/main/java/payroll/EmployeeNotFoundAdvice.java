package payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice // 1. 告诉 Spring：这个类是全局的“异常大管家”
class EmployeeNotFoundAdvice {

    @ResponseBody // 2. 将返回内容直接写入响应体（JSON）
    @ExceptionHandler(EmployeeNotFoundException.class) // 3. 明确：只管这个特定的异常
    @ResponseStatus(HttpStatus.NOT_FOUND) // 4. 重点！将状态码改为 404
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }
}