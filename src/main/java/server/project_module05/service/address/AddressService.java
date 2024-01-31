package server.project_module05.service.address;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import server.project_module05.model.dto.request.address.AddressRequest;
import server.project_module05.model.dto.response.address.AddressResponse;
import server.project_module05.model.entity.Address;
import server.project_module05.model.entity.User;
import server.project_module05.repository.IAddressRepository;
import server.project_module05.repository.IUserRepository;
import server.project_module05.security.principle.UserDetail;

import java.util.List;
import java.util.Optional;
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
        Boolean isAddressExist = addressRepository.existsByAddressIdAndUser(addressId, userRepository.findById(userDetail.getId()));
        if (!isAddressExist) {
            throw new RuntimeException("Could not find address");
        } else {
            addressRepository.deleteById(addressId);
        }
    }

    @Override
    public List<AddressResponse> getAllAddressOfUser() {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return addressRepository.findAllByUser(userRepository.findByUserId(userDetail.getId())).stream()
                .map(address -> modelMapper.map(address, AddressResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressResponse getAddressByAddressId(Long addressId) {
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Boolean isAddressExist = addressRepository.existsByAddressIdAndUser(addressId, userRepository.findById(userDetail.getId()));
        if (!isAddressExist) {
            throw new RuntimeException("Could not find address");
        }else {
            return modelMapper.map(addressRepository.findById(addressId),AddressResponse.class);
        }
    }
}
