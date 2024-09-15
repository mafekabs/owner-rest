/**
 * 
 */
package za.co.mafeka.owner_rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

/**
 * 
 */
@Builder
public record OwnerResponse(Long id, @JsonProperty("firstname") String firstName, @JsonProperty("lastname") String lastName) {

}
