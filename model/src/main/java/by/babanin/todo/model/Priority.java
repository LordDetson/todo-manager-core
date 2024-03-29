package by.babanin.todo.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

@Entity(name = "priority")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = "todos")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Priority implements Persistent<Long>, Indexable {

    @Id
    @SequenceGenerator(name = "priority-seq", sequenceName = "PRIORITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priority-seq")
    Long id;

    @Column(unique = true, nullable = false, length = 16)
    @NonNull
    String name;

    @Column(nullable = false)
    @NonNull
    long position;

    @OneToMany(mappedBy = "priority")
    Set<Todo> todos;

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Priority priority = (Priority) o;
        return name.equals(priority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
