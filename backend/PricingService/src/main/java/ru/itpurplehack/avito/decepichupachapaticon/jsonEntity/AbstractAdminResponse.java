package ru.itpurplehack.avito.decepichupachapaticon.jsonEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractAdminResponse {

    private LocalDateTime timestamp;
}
