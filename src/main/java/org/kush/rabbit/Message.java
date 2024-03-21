package org.kush.rabbit;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    List<Double> priceChange=new ArrayList<>();
}
