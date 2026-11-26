package es.safareyes.swapcloset.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ManejadorGlobalErrores {

    // 404: algo que no existe
    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ProblemDetail noEncontrado(RecursoNoEncontradoException e) {
        return construir(HttpStatus.NOT_FOUND, "Recurso no encontrado", e.getMessage());
    }

    // 409: algo que ya existe y choca
    @ExceptionHandler(ConflictoException.class)
    public ProblemDetail conflicto(ConflictoException e) {
        return construir(HttpStatus.CONFLICT, "Conflicto con los datos existentes", e.getMessage());
    }

    // 422: una regla del cliente lo impide
    @ExceptionHandler(ReglaNegocioException.class)
    public ProblemDetail reglaNegocio(ReglaNegocioException e) {
        return construir(HttpStatus.UNPROCESSABLE_ENTITY, "Regla de negocio incumplida", e.getMessage());
    }

    // 400: falla una validación del DTO (@NotBlank, @Size...), campo a campo
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail validacion(MethodArgumentNotValidException e) {
        Map<String, String> errores = new LinkedHashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

        ProblemDetail problema = construir(HttpStatus.BAD_REQUEST,
                "Datos no válidos", "Revisa los campos indicados");
        problema.setProperty("errores", errores);
        return problema;
    }

    // 400: JSON mal formado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail jsonInvalido(HttpMessageNotReadableException e) {
        return construir(HttpStatus.BAD_REQUEST, "Petición no legible",
                "El cuerpo de la petición no tiene un formato válido");
    }

    // 400: /prendas/abc cuando se espera un número
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail tipoIncorrecto(MethodArgumentTypeMismatchException e) {
        return construir(HttpStatus.BAD_REQUEST, "Parámetro no válido",
                "El valor de '" + e.getName() + "' no tiene el tipo esperado");
    }

    // 409: una restricción de la base de datos que se nos ha escapado
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail integridad(DataIntegrityViolationException e) {
        log.warn("Violación de integridad", e);
        return construir(HttpStatus.CONFLICT, "Conflicto de integridad",
                "La operación viola una restricción de la base de datos");
    }

    // 500: la red de seguridad, para cualquier cosa que no hayamos previsto
    @ExceptionHandler(Exception.class)
    public ProblemDetail errorInesperado(Exception e) {
        // Errores propios de Spring (ruta que no existe, método no permitido...)
        // ya traen su código correcto: los dejamos pasar tal cual
        if (e instanceof ErrorResponse errorSpring) {
            return errorSpring.getBody();
        }

        log.error("Error no controlado", e);   // la traza, al log
        return construir(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno",
                "Ha ocurrido un error inesperado");   // al cliente, un mensaje neutro
    }

    private ProblemDetail construir(HttpStatus estado, String titulo, String detalle) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(estado, detalle);
        problema.setTitle(titulo);
        problema.setProperty("timestamp", LocalDateTime.now());
        return problema;
    }
}