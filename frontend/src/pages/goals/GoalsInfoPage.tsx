import { Goals, GoalsGraph } from "@/modules/Goals";
import BackLink from "@/ui/BackLink.tsx";

export default function GoalsInfoPage() {
  return (
    <div className="flex flex-col gap-6">
      <BackLink to="/"></BackLink>
      <GoalsGraph />
      <Goals isFull={false} />
    </div>
  );
}
