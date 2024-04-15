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
  id: number;
  username: string;
  email: string;
  roles: Role[];
}

export interface Role {
  id: number;
  name: string;
  actions: any[]; // Replace 'any' with more specific type if possible
}

export interface Staff {
  id: number;
  firstName: string;
  lastName: string;
  user: User;
}

