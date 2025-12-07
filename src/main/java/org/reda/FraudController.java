package org.reda;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fraud")
// @CrossOrigin("*") // (Plus besoin si vous avez la classe WebConfig)
public class FraudController {

    private final FraudMLService fraudMLService;

    public FraudController(FraudMLService fraudMLService) {
        this.fraudMLService = fraudMLService;
    }

    @PostMapping("/check")
    public Map<String, Object> checkTransaction(@RequestBody TransactionRequest request) {
        // L'IA analyse UNIQUEMENT les chiffres (elle ne connait pas le nom)
        String prediction = fraudMLService.predict(request);

        // On prépare la réponse pour le dashboard
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("prediction", prediction);
        response.put("analyzed_amount", request.getAmount());
        // AJOUTER CECI : On renvoie le nom au frontend
        response.put("user_name", request.getUserName());

        return response;
    }
}