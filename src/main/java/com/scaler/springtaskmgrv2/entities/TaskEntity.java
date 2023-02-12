package com.scaler.springtaskmgrv2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tasks")
@Table(indexes = @Index(columnList = "title"))
@Getter
@Setter
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 150)
    String title;
    @Column(name = "description", nullable = true, length = 500)
    String description;
    @Column(name = "completed", nullable = false, columnDefinition = "boolean default false")
    Boolean completed;
    @Column(name = "due_date", nullable = true)
    String dueDate;
}
