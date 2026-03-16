package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate dateFromData;
        Map<String, Long> salary = new HashMap<>();
        String nameData;
        long hours = 0;
        long bid = 0;
        for (String name : names) {
            salary.put(name, 0L);
        }
        for (String arraysData : data) {
            String[] splitData = arraysData.split("\\s+");
            dateFromData = LocalDate.parse(splitData[0], formatter);
            nameData = splitData[1];
            hours = Long.parseLong(splitData[2]);
            bid = Long.parseLong(splitData[3]);
            if (salary.containsKey(splitData[1])
                    && (!dateFromData.isBefore(from)
                    && !dateFromData.isAfter(to))) {
                salary.put(nameData, salary.get(nameData) + (hours * bid));
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            report.append(name)
                    .append(" - ")
                    .append(salary.get(name))
                    .append(System.lineSeparator());
        }
        int sepLen = System.lineSeparator().length();
        report.setLength(report.length() - sepLen);
        return report.toString();
    }
}
