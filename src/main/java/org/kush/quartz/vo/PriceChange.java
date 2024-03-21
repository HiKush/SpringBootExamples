package org.kush.quartz.vo;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceChange {
    Double priceChange;
    public byte getBytes(){
        return priceChange.byteValue();
    }
    @Override
    public String toString(){
       return priceChange.toString();
    }
}
