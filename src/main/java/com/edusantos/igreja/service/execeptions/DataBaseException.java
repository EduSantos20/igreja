package com.edusantos.igreja.service.execeptions;

public class DataBaseException extends RuntimeException{

    public DataBaseException(String mensagem){
        super(mensagem);
    }
}
