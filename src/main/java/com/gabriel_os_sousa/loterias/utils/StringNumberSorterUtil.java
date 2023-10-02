package com.gabriel_os_sousa.loterias.utils;

import java.util.ArrayList;
import java.util.List;

public class StringNumberSorterUtil {

  public static List<String> sortStringNumbers(List<String> numbers) {
    // Clona a lista para não modificar a lista original
    List<String> sortedNumbers = new ArrayList<>(numbers);

    // Ordena a lista de strings
    sortedNumbers.sort((a, b) -> {
      // Converte as strings para números inteiros e compara
      int intA = Integer.parseInt(a);
      int intB = Integer.parseInt(b);
      return Integer.compare(intA, intB);
    });

    return sortedNumbers;
  }
}