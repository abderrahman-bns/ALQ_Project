export interface UserRegitrationRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  roles: Role[];
}

enum Role {
  ROLE_USER = 'ROLE_USER',
  ROLE_ADMIN = 'ROLE_ADMIN',
  ROLE_CONDIDATE = 'ROLE_CONDIDATE',
}

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  roles: Role[];
  token: string;
}

export interface UserLoginRequest {
  email: string;
  password: string;
}

export interface Posts {
  idPoste: number;
  title: string;
  description: string;
}
