package com.felipecpdev.demorabbitmqspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {

    private Integer notificationId;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String message;
}