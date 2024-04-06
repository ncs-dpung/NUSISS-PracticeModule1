export interface Staff {
  staff_id?: number | null | undefined;
  first_name: string;
  last_name: string;
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

