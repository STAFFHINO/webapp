package storage.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
@Builder
public class CellDto {
    private int id;
    private boolean status;
    private String contents;
    private UUID userId;
    private Date openingDate;
}