package server.project_module05.model.dto.response.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponse {
    private Long addressId;
    private String receiveName;
    private String fullAddress;
    private String phone;
}
