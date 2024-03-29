package server.project_module05.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.project_module05.model.dto.request.address.AddressRequest;
import server.project_module05.model.dto.response.address.AddressResponse;
import server.project_module05.service.address.AddressService;
import server.project_module05.service.address.IAddressService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api.myservice.com/v1/user/account/address")
public class AddressController {
    private final AddressService addressService;
    @PostMapping
    public ResponseEntity<AddressResponse> handleAddNewAddress(@RequestBody AddressRequest addressRequest){
        return ResponseEntity.ok().body(addressService.addNewAddress(addressRequest));
    }
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> handleDeleteAddressById(@PathVariable Long addressId){
        addressService.deleteAddressById(addressId);
        return ResponseEntity.ok("Delete successfully");
    }
    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddressOfUser(){
        return new ResponseEntity<>(addressService.getAllAddressOfUser(), HttpStatus.OK);
    }
    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponse> getAddressByAddressId(@PathVariable Long addressId){
        return new ResponseEntity<>(addressService.getAddressByAddressId(addressId),HttpStatus.OK);
    }
}
