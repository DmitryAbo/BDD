package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@AllArgsConstructor
@Getter
@Setter
public class CardState {
    private int balance;
    private String pan;
    private String cardId;
}
