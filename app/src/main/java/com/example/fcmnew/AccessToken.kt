package com.example.fcmnew

import com.google.auth.oauth2.GoogleCredentials
import com.google.common.collect.Lists
import java.io.ByteArrayInputStream
import java.io.InputStream

class AccessToken {

    companion object {
        private val FIREBASE = "https://www.googleapis.com/auth/firebase.messaging"

        fun getAccessToken(): String {
            try {
                val jsonString = "{\n" +
                        "  \"type\": \"service_account\",\n" +
                        "  \"project_id\": \"fcmnew-f697f\",\n" +
                        "  \"private_key_id\": \"a759b9dd58e180e3eaec39ddb0be06eb2b844304\",\n" +
                        "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDeyIBe9m67JvTc\\ntgAETyDipHPIpd+q0re53WIcas1kn1vk48iWCbDl38EWvsbmpTZ1nfAGjznLXAl/\\nYUqXjuEl9GbLpgUAUg4M/zbBetsFip20e3OfBD6Hqw2rPIyF8C/0RHFlXSz5C9Vp\\n9Mh9O4hNp4O3qwvs/WpfqY1+Q1tQAQ8S0x0Ktpzzum2m5t1sOJGc3qdpmN3FXANN\\nlABR+ltdabyIrZKs7pnszrj7WLlPafWUA6oHEfDhFCUnN6Qaw8Jux/KDhmn+Kcfv\\n3r8sr23INLhZDAihTVqfcSOl2FP9nDUk97gcyYOheKEu6sAp7d6yLw0cWGXXVXch\\nKuf3PB0ZAgMBAAECggEAGUD3NEyRDswCs8Y5yENEAg0MRvFuokmSpBIRXhFJSR+7\\nZf3gLgnZMKWlmLN1jo2p17lYjRXNQl0jgnn/36BFq2zB/lHKfVfP2e9QQKxxiXZG\\nHEBmQd9US0K7oO6TK1enx/O+OvRN9ck+nu0tkOyfrdXQW5CbIHk1hrSMooR2JGJU\\nGWjJGc7x69hwSIQTk8VId6thBTNOolZ6biPRC6mNGrMVgHr56B7VTtNqJ20y2z8a\\n50fh+AtDTtGjWp8c5lp4uDoJwproi5ZVrNyy00R7lbKrMWkGWSewDLP6ljf1WEi/\\nGbPXddXL5yZZjwYrAw2JRwVJ7vo/qeUz2LLG79vMAQKBgQD97Yp75Wp8HtUnAziN\\nZ9mY4Lw8wp9NUvZlV/ojJhcN35mJ/C4hO9+R45R/ubXe9qTWGwJ+4ONTBjZoZXaC\\no+fVpgyctSUmhknyhnl3pjf2+gvXS4XjgBJKaYCPvH7f9d5zDFF+c1vSBc9tUemJ\\ntRZkeIgIejg0fHZwsQnQFGnKmQKBgQDgmeYXkA0uEkFzZ2YVBxBK0bYe9YgGliCr\\n5OoCrm9AQBb4bjnSTdmatSfbf1dOzgCyxiHlgkjUBcUpZhxCHao7U+wYjKR9K8Hh\\nFjKIABBU7OOM3Yx+cVkSDtyUzPhuIRmu2jXqfCPJfYja6QpgsjrQHRmzeM1u7eFX\\nxBXw4+72gQKBgQC01IftSWg2sgJbgPiyAsW8EDcdEgA+IZmkfC1/AG4kjBqcYEE9\\nm5TQIQCsoMT63ixu6NczIdXhVyhB2he2WyELTe/nXTzynWll1AwGgBGUyWbLUFYt\\nuBPOeCl8suCrfz2GMryKT36RJ2AbWtz0G0iyDf/aAaerAQObpwf07cSU6QKBgQDL\\nL27Qcij6PNmpJSlcbH94sC8p4sWj77yuxPBObK+IWHJvXIhCkNsC8k+Uaqb5S7Om\\nB0gy8KIh7PZodU+rIFsYQNuded50vlku333nOueLbKKOlP68hJ4nj/pNQwCb4KXz\\nYMTV1Ggrp71fDOOuho5eJ3VQv0BZ0inB1EplGk7cAQKBgCc+gpbDY+obcFsUYh7p\\nROkSJDR3kK7w7M+tU2MBwWNVSX8dX29+OIKtIN69Y4hL4jCGrB3xNQL5FZwPnGrD\\nSAP1lDHoTroWVgQ3SJmIQbj8aX1Er4nZHaO8QJqSh9hxrTP97yXixeaQMS4QIvQg\\nf7kjo6vP1zXayjavSL8wn2RH\\n-----END PRIVATE KEY-----\\n\",\n" +
                        "  \"client_email\": \"firebase-adminsdk-r70cl@fcmnew-f697f.iam.gserviceaccount.com\",\n" +
                        "  \"client_id\": \"100349517091543049593\",\n" +
                        "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                        "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                        "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                        "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-r70cl%40fcmnew-f697f.iam.gserviceaccount.com\",\n" +
                        "  \"universe_domain\": \"googleapis.com\"\n" +
                        "}\n"

                val stream: InputStream = ByteArrayInputStream(jsonString.toByteArray(Charsets.UTF_8))
                val credentials = GoogleCredentials.fromStream(stream).createScoped(Lists.newArrayList(FIREBASE))
                credentials.refresh()
                return credentials.accessToken.tokenValue
            } catch (e: Exception) {
                return e.message.toString()
            }
        }
    }

}