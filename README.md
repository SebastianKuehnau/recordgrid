# Record Grid

To display record types in a Vaadin grid with automatically adding the fields of a record.

## usage

You can use the component as you know it from Vaadin Grid. You need to pass the record type as a parameter to the constructor.

```
RecordGrid<PersonRecord> recordGrid = new RecordGrid<>(PersonRecord.class);

recordGrid.setColumns("name", "birthday", "number");
recordGrid.setItems(personRecordSet);
add(recordGrid);
```

This is the very first version and coding will be continued ...