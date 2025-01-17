/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.manager.pojo.consume.pulsar;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.inlong.manager.common.enums.ErrorCodeEnum;
import org.apache.inlong.manager.common.exceptions.BusinessException;

import javax.validation.constraints.NotNull;

/**
 * Inlong group dto of Pulsar
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Inlong group dto of Pulsar")
public class ConsumePulsarDTO {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @ApiModelProperty("Whether to configure the dead letter queue, 0: not configure, 1: configure")
    private Integer isDlq;

    @ApiModelProperty("The name of the dead letter queue Topic")
    private String deadLetterTopic;

    @ApiModelProperty("Whether to configure the retry letter queue, 0: not configure, 1: configure")
    private Integer isRlq;

    @ApiModelProperty("The name of the retry letter queue topic")
    private String retryLetterTopic;

    /**
     * Get the dto instance from the request
     */
    public static ConsumePulsarDTO getFromRequest(ConsumePulsarRequest request) {
        return ConsumePulsarDTO.builder()
                .isDlq(request.getIsDlq())
                .deadLetterTopic(request.getDeadLetterTopic())
                .isRlq(request.getIsRlq())
                .retryLetterTopic(request.getRetryLetterTopic())
                .build();
    }

    /**
     * Get the dto instance from the JSON string.
     */
    public static ConsumePulsarDTO getFromJson(@NotNull String extParams) {
        try {
            return OBJECT_MAPPER.readValue(extParams, ConsumePulsarDTO.class);
        } catch (Exception e) {
            throw new BusinessException(ErrorCodeEnum.CONSUMER_INFO_INCORRECT.getMessage() + ": " + e.getMessage());
        }
    }

}
