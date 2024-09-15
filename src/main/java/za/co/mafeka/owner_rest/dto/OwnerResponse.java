/**
 * 
 */
package za.co.mafeka.owner_rest.dto;

import lombok.Builder;

/**
 * 
 */
@Builder
public record OwnerResponse(Long id, String firstname, String lastName) {

}
