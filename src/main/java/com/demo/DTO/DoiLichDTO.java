package com.demo.DTO;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DoiLichDTO {
    @NotNull
    private LocalDate date;
    @NotNull
    private Integer idcalichhen;

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public @NotNull Integer getIdcalichhen() {
        return idcalichhen;
    }

    public void setIdcalichhen(@NotNull Integer idcalichhen) {
        this.idcalichhen = idcalichhen;
    }
}
