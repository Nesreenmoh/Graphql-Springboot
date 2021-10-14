package com.graphql.graphqlsql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
  private LocalDate birthday;
  private OffsetTime workTime;
  private OffsetDateTime bornAt;
  private String profileLink;

}
