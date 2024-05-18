import Balance from "@/modules/Balance";
import Profile from "@/modules/Profile";

export default function ProfilePage() {
  return (
    <div className="flex flex-col gap-6">
      <Balance />
      <Profile />
    </div>
  );
}
