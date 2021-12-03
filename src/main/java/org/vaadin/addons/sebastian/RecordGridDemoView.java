package org.vaadin.addons.sebastian;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import java.time.LocalDateTime;
import java.util.List;

@Route("")
public class RecordGridDemoView extends Div {

    private final List<PersonRecord> personRecordSet = List.of(
            new PersonRecord("Donald Duck", LocalDateTime.of(1952, 06, 25, 0, 0), 45),
            new PersonRecord("Micky Mouse", LocalDateTime.of(1654, 12, 5, 0, 0), 74),
            new PersonRecord("Goofy", LocalDateTime.of(2020, 1, 14, 0, 0), 7)
    );

    public RecordGridDemoView() {
        RecordGrid<PersonRecord> recordGrid = new RecordGrid<>(PersonRecord.class);

        recordGrid.setColumns("name", "birthday", "number");
        recordGrid.setItems(personRecordSet);
        add(recordGrid);

    }

    public record PersonRecord(String name, LocalDateTime birthday, Integer number) {}
}
