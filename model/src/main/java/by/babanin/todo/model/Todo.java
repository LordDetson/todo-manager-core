package by.babanin.todo.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

@Entity(name = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Todo implements Persistent<Long>, Indexable {

    @Id
    @SequenceGenerator(name = "todo-seq", sequenceName = "TODO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo-seq")
    Long id;

    @Column(nullable = false, length = 32)
    @NonNull
    String title;

    @Column(length = 1024)
    String description;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    Priority priority;

    @Column(nullable = false)
    @NonNull
    Status status;

    @Column(nullable = false)
    @NonNull
    LocalDate creationDate;

    @Column(nullable = false)
    @NonNull
    LocalDate plannedDate;

    LocalDate completionDate;

    @Column(nullable = false)
    long position;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
