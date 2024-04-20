/*export interface Staff {
  id?: number | null | undefined;
  firstName: string;
  lastName: string;
  position: string;
  department: string;
  email: string;
  phone_number: string;
  address: string;
  created_at: Date;
  updated_at: Date;
  created_by: string;
  updated_by: string;
}*/

export interface User {
  id?: number | null | undefined;
  username: string;
  email: string;
  roles: Role[];
}

export interface Role {
  id?: number | null | undefined;
  name: string;
  actions: any[]; // Replace 'any' with more specific type if possible
}

export interface Staff {
  id?: number | null | undefined;
  firstName: string;
  lastName: string;
  user: User;
}

