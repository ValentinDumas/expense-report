#!/bin/bash

mvn -X clean verify sonar:sonar -Dsonar.projectKey=trivia  -Dsonar.host.url=http://localhost:9000 -Dsonar.login=sqa_46cc31393e2a58e42a78877ee5fa63ccea27dbbf



