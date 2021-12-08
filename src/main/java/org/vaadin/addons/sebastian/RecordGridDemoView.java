package org.vaadin.addons.sebastian;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.List;

@Route("")
public class RecordGridDemoView extends VerticalLayout {

    private final List<PersonRecord> personRecordSet = List.of(
            new PersonRecord("Donald Duck", LocalDate.of(1952, 06, 25), 45),
            new PersonRecord("Micky Mouse", LocalDate.of(1654, 12, 5), 74),
            new PersonRecord("Goofy", LocalDate.of(2020, 1, 14), 7)
    );

    public RecordGridDemoView() {
        RecordGrid<PersonRecord> recordGrid = new RecordGrid<>(PersonRecord.class);
        recordGrid.setColumns("name", "birthday", "number");
        recordGrid.setItems(personRecordSet);
        add(recordGrid);
    }

    public record PersonRecord(String name, LocalDate birthday, Integer number) {}
}
