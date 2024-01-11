package com.example.resttemplate;

import lombok.Data;
/**
 * (Класс домена)
 * Этот класс использует библиотеку Lombok для автоматической генерации методов получения / установки с аннотацией @Data.
 */
@Data
public class UserData {
    private String id;
    private String userName;
    private String data;
}
