package com.interview.code.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SynalogikResponse {
    Integer wordCount;
    BigDecimal averageWordLength;
    HashMap<Integer, Integer> lengthOfWordAndAmount;
}
