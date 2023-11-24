package yiu.aisl.carpool.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yiu.aisl.carpool.Dto.CarpoolDto;
import yiu.aisl.carpool.Dto.CarpoolRequest;
import yiu.aisl.carpool.Dto.WaitRequest;
import yiu.aisl.carpool.repository.CarpoolRepository;
import yiu.aisl.carpool.security.CustomUserDetails;
import yiu.aisl.carpool.service.CarpoolService;

@RestController
@RequestMapping("/carpool")
@RequiredArgsConstructor
public class CarpoolController {
  private final CarpoolRepository carpoolRepository;
  private final CarpoolService carpoolService;
  @PostMapping("/create")
  public ResponseEntity<Boolean> carpoolCreate(@RequestBody CarpoolRequest request)
          throws Exception {
    return new ResponseEntity<>(carpoolService.create(request), HttpStatus.OK);
  }

  @PutMapping("/update/{carpoolNum}")
  public ResponseEntity<Object> carpoolUpdate(
          @AuthenticationPrincipal CustomUserDetails userDetails,
          @PathVariable int carpoolNum,
          @RequestBody CarpoolDto carpoolDto) {
    carpoolService.update(userDetails, carpoolNum, carpoolDto);
    return ResponseEntity.ok("업데이트 성공");
  }

  @DeleteMapping("/delete/{carpoolNum}")
  public ResponseEntity<Object> carpoolDelete(
          @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable int carpoolNum) {
    carpoolService.delete(userDetails, carpoolNum);
    return ResponseEntity.ok("삭제 성공");
  }

  @PostMapping("/{carpoolNum}/apply")
  public ResponseEntity<Boolean> carpoolApply(@RequestBody WaitRequest request, @PathVariable int carpoolNum)
          throws  Exception {
    return new ResponseEntity<>(carpoolService.apply(request, carpoolNum), HttpStatus.OK);
  }
}