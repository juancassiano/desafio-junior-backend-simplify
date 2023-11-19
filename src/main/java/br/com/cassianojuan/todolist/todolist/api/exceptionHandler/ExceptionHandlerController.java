package br.com.cassianojuan.todolist.todolist.api.exceptionHandler;

import java.io.StringWriter;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.cassianojuan.todolist.todolist.domain.exceptions.TodoNaoEncontradoException;

@RestControllerAdvice
public class ExceptionHandlerController {
  
    @Autowired
    private MessageSource messageSource;
    URI path = URI.create("http://localhost:8080/errors");

    @ExceptionHandler(TodoNaoEncontradoException.class)
    private ResponseEntity<ProblemDetail> handleTodoNaoEncontradoException(TodoNaoEncontradoException exception){
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,sw.toString()
        );
        problemDetail.setInstance(URI.create("/todo-nao-encontado"));
        problemDetail.setTitle(exception.getMessage());
        problemDetail.setProperty("descricao", "Esse Todo não foi encontrado ou não existe");
        problemDetail.setProperty("timesteamp", Instant.now());
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            errorMessages.add(errorMessage);
        }
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                sw.toString());
        problemDetail.setTitle("Erro de validação");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("errors", errorMessages);
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ProblemDetail> handleHttpMessageNotReadable(HttpMessageNotReadableException exception){
        StringWriter sw = new StringWriter();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                sw.toString());
        problemDetail.setTitle("Erro de validação");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setType(path);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);

    }
}
