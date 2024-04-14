export interface Staff {
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
}

