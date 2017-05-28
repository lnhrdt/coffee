package friends.rest

import org.springframework.cloud.contract.spec.Contract

Contract.make {
  request {
    method GET()
    url "/api/friends"
    headers {
      contentType(applicationJson())
    }
  }
  response {
    status 200
    body(
        data: [
            [
                id  : $(producer(regex(uuid())), consumer("2162c86c-4f7f-431f-9e0b-8d245c5f994a")),
                name: $(producer(anyNonEmptyString()), consumer("Matthew Parin"))
            ], [
                id  : $(producer(regex(uuid())), consumer("caf303cd-fb06-4b16-97f2-2d2bc91afb49")),
                name: $(producer(anyNonEmptyString()), consumer("Robert Neumann"))
            ], [
                id  : $(producer(regex(uuid())), consumer("72844ace-3859-4e1d-81d2-d03c816bd2b4")),
                name: $(producer(anyNonEmptyString()), consumer("Scott Sisil"))
            ]
        ]
    )
    headers {
      contentType(applicationJson())
    }
  }
}
