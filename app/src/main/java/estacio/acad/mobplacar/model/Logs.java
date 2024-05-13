package estacio.acad.mobplacar.model;

import java.util.Arrays;
import java.util.List;

public class
Logs {
   public static List<Log> fakeLog(){
       return Arrays.asList(
         Log.LogBuilder.builder()
                 .setUser("Asher Martins")
                 .setDate("04/04/2023")
                 .setPreview("Fez Login")
                 .setHorario("11:00")
                 .setSala("")
                 .build(),

               Log.LogBuilder.builder()
               .setUser("Marco Guimarães")
               .setDate("06/04/2023")
               .setPreview("Retirou uma chave")
                       .setHorario("13:00")
                       .setSala("B103")
               .build(),

               Log.LogBuilder.builder()
                       .setUser("Hiago Marques")
                       .setDate("01/04/2023")
                       .setPreview("Retirou uma chaves")
                       .setHorario("15:00")
                       .setSala("B101")
                       .build()
               ,

               Log.LogBuilder.builder()
                       .setUser("Hiago Marques")
                       .setDate("01/04/2023")
                       .setPreview("Fez Login")
                       .setHorario("14:58")
                       .setSala("")
                       .build(),

               Log.LogBuilder.builder()
                       .setUser("Iago Sinésio")
                       .setDate("06/04/2023")
                       .setPreview("Entregou uma chave")
                       .setHorario("12:00")
                       .setSala("A102")
                       .build(),

               Log.LogBuilder.builder()
                       .setUser("Vinicius Ferraz")
                       .setDate("01/04/2023")
                       .setPreview("Entregou uma chave")
                       .setHorario("11:00")
                       .setSala("A105")
                       .build()
               ,

               Log.LogBuilder.builder()
                       .setUser("Vinicius Ferraz")
                       .setDate("02/04/2023")
                       .setPreview("Fez Login")
                       .setHorario("10:00")
                       .setSala("")
                       .build()
               ,

               Log.LogBuilder.builder()
                       .setUser("Asher Martins")
                       .setDate("01/04/2023")
                       .setPreview("Fez Login")
                       .setHorario("9:00")
                       .setSala("")
                       .build(),

               Log.LogBuilder.builder()
                       .setUser("Iago Sinésio")
                       .setDate("06/04/2023")
                       .setPreview("Retirou uma chave")
                       .setHorario("13:00")
                       .setSala("B111")
                       .build()
               ,

               Log.LogBuilder.builder()
                       .setUser("Iago Sinésio")
                       .setDate("06/04/2023")
                       .setPreview("Fez Login")
                       .setHorario("12:56")
                       .setSala("")
                       .build(),

               Log.LogBuilder.builder()
                       .setUser("Marco Guimarães")
                       .setDate("06/04/2023")
                       .setPreview("Fez Login")
                       .setHorario("12:00")
                       .setSala("")
                       .build()
       );

   }


}
