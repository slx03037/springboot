在common的模块服务下
新建文件 src/main/proto/hello.proto

context(内容):

syntax = "proto3";

option java_multiple_files = true;
option java_package = "<your-package-name-goes-here>.proto";
option java_outer_classname = "HelloWorldProto";

// The greeting service definition.
service Simple {
// Sends a greeting
rpc SayHello(HelloRequest) returns (HelloReply) {}
rpc StreamHello(HelloRequest) returns (stream HelloReply) {}
}

// The request message containing the user's name.
message HelloRequest {
string name = 1;
}

// The response message containing the greetings
message HelloReply {
string message = 1;
}

使用maven命令构建
mvn clean package