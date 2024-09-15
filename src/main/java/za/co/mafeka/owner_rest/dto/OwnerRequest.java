/**
 * 
 */
package za.co.mafeka.owner_rest.dto;

import lombok.Builder;

/**
 * 
 */
@Builder
public record OwnerRequest(String firstname, String lastname) {

}
