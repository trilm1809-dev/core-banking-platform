package com.corebankingplatform.server.entites;

import org.springframework.format.annotation.DateTimeFormat;

public class BaseEntity {

    private DateTimeFormat createDate;
    private DateTimeFormat updateDate;
    private Boolean isDeleted;
}
