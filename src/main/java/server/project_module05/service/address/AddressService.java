package server.project_module05.service.address;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.address.AddressRequest;
import server.project_module05.model.dto.response.address.AddressResponse;
import server.project_module05.model.entity.Address;
import server.project_module05.repository.IAddressRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.security.principle.UserDetail;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {
    private final IAddressRepository addressRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddressResponse addNewAddress(AddressRequest addressRequest) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Address newAddress = new Address();
        newAddress.setUser(userRepository.findByUserId(userDetail.getId()));
        newAddress.setReceiveName(addressRequest.getReceiveName());
        newAddress.setFullAddress(addressRequest.getFullAddress());
        newAddress.setPhoneNumber(addressRequest.getPhone());
        addressRepository.save(newAddress);
        return modelMapper.map(newAddress, AddressResponse.class);
    }

    @Override
    public void deleteAddressById(Long addressId) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Address address = addressRepository.findByAddressIdAndUser(addressId, userRepository.findByUserId(userDetail.getId()));

        if (address == null) {
            throw new RuntimeException("Could not find address");
        }
        addressRepository.delete(address);
    }

    @Override
    public List<AddressResponse> findAll() {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Address> addressList = addressRepository.findAllByUser(userRepository.findByUserId(userDetail.getId()));
        if (addressList == null) {
            throw new RuntimeException("Could not find address");
        }
        return addressList.stream().map(address -> modelMapper.map(address, AddressResponse.class)).collect(Collectors.toList());
    }

    @Override
    public AddressResponse findAddressById(Long addressId) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Address address = addressRepository.findByAddressIdAndUser(addressId, userRepository.findByUserId(userDetail.getId()));
        if (address == null){
            throw new RuntimeException("Could not find address");
        }
        return modelMapper.map(address, AddressResponse.class);
    }
}
