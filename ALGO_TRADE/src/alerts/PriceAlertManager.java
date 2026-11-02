package alerts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriceAlertManager {
    private final List<PriceAlert> alerts = new ArrayList<>();

    public boolean addAlert(PriceAlert alert) {
        if (alert == null) {
            throw new IllegalArgumentException("Alert cannot be empty.");
        }

        boolean alreadyExists = alerts.stream().anyMatch(existing ->
                existing.getStock().getSymbol()
                        .equalsIgnoreCase(alert.getStock().getSymbol())
                        && existing.getConditionName()
                                .equals(alert.getConditionName())
                        && existing.getTargetPrice()
                                == alert.getTargetPrice());

        if (alreadyExists) {
            return false;
        }

        alerts.add(alert);
        return true;
    }

    public List<PriceAlert> getAlerts() {
        return Collections.unmodifiableList(
                new ArrayList<>(alerts));
    }

    public List<PriceAlert> getTriggeredAlerts() {
        return alerts.stream()
                .filter(PriceAlert::isTriggered)
                .toList();
    }

    public PriceAlert removeAlert(int number) {
        if (number < 1 || number > alerts.size()) {
            throw new IllegalArgumentException(
                    "That alert number does not exist.");
        }

        return alerts.remove(number - 1);
    }

    public boolean isEmpty() {
        return alerts.isEmpty();
    }
}
