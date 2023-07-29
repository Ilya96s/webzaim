package ru.test.webzaim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Credit - объект, описывающий модель данных таблицы кредитов
 *
 * @author Ilya Kaltygin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentString;

    private LocalDate paymentDate;
}
