import Balance from "@/modules/Balance";
import Profile from "@/modules/Profile";
import BackLink from "@/ui/BackLink.tsx";

export default function ProfilePage() {
  return (
    <div className="flex flex-col gap-6 max-w-lg mx-auto">
        <BackLink to="/"/>
      <Balance />
      <Profile />
    </div>
  );
}
