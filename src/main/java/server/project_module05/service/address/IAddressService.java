package server.project_module05.service.address;

import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.address.AddressRequest;
import server.project_module05.model.dto.response.address.AddressResponse;

import java.util.List;

@Service
public interface IAddressService {
    AddressResponse addNewAddress(AddressRequest addressRequest);
    void deleteAddressById(Long addressId);

    List<AddressResponse> findAll();

    AddressResponse findAddressById(Long addressId);
}
