package handler;

import exception.PrenotazioneNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class PrenotazioneGlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handlerMethodArgumentNotValidException( MethodArgumentNotValidException e ){
        return new ResponseEntity<>(getRet("400", e.getFieldErrors().toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PrenotazioneNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handlerBookNotFoundException( PrenotazioneNotFoundException e ){
        return new ResponseEntity<>(getRet("404", "la prenotazione richiesta non è presente sulla base dati" ), HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> getRet(String errorCode, String errorMessage) {
        Map<String, Object> ret = new HashMap<>();

        ret.put("timestamp", LocalDateTime.now());
        ret.put("error", errorCode);
        ret.put("message", errorMessage);
        return ret;
    }


}
