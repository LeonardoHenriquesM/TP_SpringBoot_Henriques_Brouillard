package tp.leothy.springboot.service;

import java.util.List;
import java.util.Map;

public interface ServiceStatistiques {

    Map<String, Long> compterLivresParCategorie();

    List<Map<String, Object>> topAuteurs(int limite);
}
