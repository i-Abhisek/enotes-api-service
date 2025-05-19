package com.abhisek.dto;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {
	
	private Integer id;

    private String name;

    private String description;
    
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "create_on")
    private Date createOn;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "update_on")
    private Date updateOn;

}
