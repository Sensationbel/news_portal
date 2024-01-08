package by.bulaukin.news_portal.model;

import by.bulaukin.news_portal.model.enums.NewsType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Convert(converter = TypeConverter.class)
    @Column(name = "type")
    private NewsType type;

    @ToString.Exclude
    @OneToMany(mappedBy = "category")
    private List<News> newsList = new ArrayList<>();

    public static class TypeConverter implements AttributeConverter<NewsType, Character> {

        @Override
        public Character convertToDatabaseColumn(NewsType attribute) {
            if(attribute == null) {
                return null;
            }
            return attribute.getCode();
        }

        @Override
        public NewsType convertToEntityAttribute(Character dbData) {
            if(dbData == null) {
                return null;
            }
            return NewsType.fromCode(dbData);
        }
    }

}
