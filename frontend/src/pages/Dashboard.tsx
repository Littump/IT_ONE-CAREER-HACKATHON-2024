import Balance from "@/modules/Balance";
import { Goals } from "@/modules/Goals";
import { Operations } from "@/modules/Operations";

export default function DashboardPage() {
  return (
    <div className="flex flex-col gap-6">
      <Balance />
      <div className="flex flex-col md:grid md:grid-cols-2 gap-6">
        <Goals />
        <Operations />
      </div>
    </div>
  );
}
