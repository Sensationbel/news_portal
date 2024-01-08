package by.bulaukin.news_portal.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;

@Getter
@RequiredArgsConstructor
public enum NewsType {
    SPORT('S'),
    ECONOMIC('E'),
    COMPUTER_SINCE('C');

    private final Character code;

    public static NewsType fromCode(Character code) {
        return switch (code) {
            case ('S') -> SPORT;
            case ('E') -> ECONOMIC;
            case ('C') -> COMPUTER_SINCE;
            default ->
                    throw new UnsupportedOperationException(MessageFormat.format("The code: {0} is not supported", code));
        };
    }
}
